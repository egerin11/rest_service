import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { EMPTY, Subscription, catchError, concat, forkJoin } from 'rxjs';
import { Cat } from 'src/app/model/cat';
import { CatAndFactService } from 'src/app/services/cat-and-fact.service';
import { DialogBoxComponent } from '../dialog-box/dialog-box.component';
import { ConfirmDialog } from 'src/app/model/confirm-dialog';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-cat-details',
  templateUrl: './cat-details.component.html',
  styleUrls: ['./cat-details.component.css']
})
export class CatDetailsComponent implements OnInit {
  cat: Cat;
  private id: number;
  catSubscription: Subscription;


  constructor(private router: Router,
    private route: ActivatedRoute,
    private catService: CatAndFactService,
    public dialog: MatDialog) { }



  updateCat(cat: Cat): void {
    this.catService.updateCat(cat, this.id).subscribe(
      (data) => {
        console.log('Cat updated:', data);
        this.getCatData();
      }
    );
  }

  getCatData(): void {
    this.catSubscription = this.catService.getCatById(this.id).subscribe((cat) => {
      this.cat = cat;
      console.log(this.cat);
    });
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.getCatData();
  }

  ngOnDestroy() {
    this.catSubscription.unsubscribe();
  }

  openDialog(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '400px';
    dialogConfig.height = '500px';
    dialogConfig.disableClose = true;

    const dialogRef = this.dialog.open(DialogBoxComponent, dialogConfig);

    dialogRef.afterClosed().subscribe((data) => {
      if (data) {
        this.updateCat(data);
      }
    });
  }

  goToHome() {
    this.router.navigate(['/']);
  }

  deleteCat() {
    if (!this.cat || !this.cat.id) {
      console.error('Кот не найден или нет идентификатора кота');
      return;
    }

    const factIds = this.cat.facts.map(fact => fact.id);
    const deleteFactsObservables = factIds.map(factId => this.catService.deleteFact(factId).pipe(catchError(error => {
      console.error('Ошибка при удалении факта:', error);
      return EMPTY;
    })));
    forkJoin(deleteFactsObservables).subscribe(() => {
      console.log('Все факты успешно удалены');
    });

    const deleteOwnerObservables = this.cat.owners.map(owner => this.catService.deleteCatToOwner(this.cat.id, owner.id).pipe(catchError(error => {
      console.error('Ошибка при удалении хозяина:', error);
      return EMPTY;
    })));
    forkJoin(deleteOwnerObservables).subscribe(() => {
      console.log('Все хозяева успешно удалены');
    });

    this.catService.deleteCat(this.cat.id).subscribe(
      () => {
          this.goToHome();
        console.log('Кот и связанные данные успешно удалены');
      }
    );
  }

  confirmDialog(): void {
    const message = `Are you sure you want to do this?`;

    const dialogData = new ConfirmDialog("Confirm Action", message);

    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: "400px",
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      if(dialogResult) this.deleteCat();
    });
  }

}
