import { Component } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CatAndFactService } from 'src/app/services/cat-and-fact.service';
import { DialogBoxComponent } from '../../dialog-box/dialog-box.component';
import { Cat } from 'src/app/model/cat';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  durationInSeconds=1.5;
  constructor(private router: Router,
    public dialog: MatDialog,
    private catSevice:CatAndFactService,
    private snackBar:MatSnackBar) {}

  goToHome() {
    this.router.navigate(['/']);
  }
  openSnackBar(message: string) {
    this.snackBar.open(message, '', {
      duration: this.durationInSeconds * 1000,
    });
  }
  
postCat(cat:Cat){
  return this.catSevice.postCat(cat).subscribe((data)=>{this.catSevice.setNewCat(data);});
}

  openDialog(): void {
    let dialogConfig=new MatDialogConfig();
    dialogConfig.width='400px';
    dialogConfig.disableClose = true;
    const dialogRef = this.dialog.open(DialogBoxComponent ,dialogConfig);

    dialogRef.afterClosed().subscribe(data => {
      if (data) {
        this.catSevice.postCat(data).subscribe(newCat => {
          this.catSevice.setNewCat(newCat); 
          this.openSnackBar(newCat.name);
        });
      }
    });
  }


}
