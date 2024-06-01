import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject, Subscription, switchMap, takeUntil, tap } from 'rxjs';
import { Cat } from 'src/app/model/cat';
import { CatAndFactService } from 'src/app/services/cat-and-fact.service';

@Component({
  selector: 'app-get-all-cats',
  templateUrl: './get-all-cats.component.html',
  styleUrls: ['./get-all-cats.component.css']
})
export class GetAllCatsComponent implements OnInit ,OnDestroy {
  cats: Cat[] = [];
  catSubscription: Subscription;
  private unsubscribe$ = new Subject<void>();

  constructor(private catService: CatAndFactService,private router: Router) { }

  ngOnInit(): void {
    this.catSubscription = this.catService.getCats()
      .pipe(
        tap(cats => this.cats = cats), 
        switchMap(() => this.catService.newCat$), 
        takeUntil(this.unsubscribe$)
      )
      .subscribe(newCat => {
        if (newCat) {
          this.cats.push(newCat);
        }
      });
  }

  ngOnDestroy(): void {
    if (this.catSubscription) this.catSubscription.unsubscribe();
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
  goToCatDetails(id: number | undefined): void {
    if (id !== undefined) {
      this.router.navigate(['cat', id]);
    } 
  }
}
