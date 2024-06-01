import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cat } from '../model/cat';
import { BehaviorSubject, Observable } from 'rxjs';
import { CatFact } from '../model/cat-fact';
import { Owner } from '../model/owner';

@Injectable({
  providedIn: 'root'
})
export class CatAndFactService {
  private newCatSubject = new BehaviorSubject<Cat | null>(null);
  newCat$ = this.newCatSubject.asObservable();

  setNewCat(cat: Cat): void {
    this.newCatSubject.next(cat);
  }

  private readonly API_URL = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public getCats(): Observable<Cat[]> {
    return this.http.get<Cat[]>(`${this.API_URL}/list/cats`);
  }
  public getCatById(id: number): Observable<Cat> {
    return this.http.get<Cat>(`${this.API_URL}/list/cat/${id}`);
  }
  postCat(cat: Cat): Observable<Cat> {
    return this.http.post<Cat>(`${this.API_URL}/list/add-cat`, cat);
  }
  updateCat(cat: Cat, id?: number): Observable<Cat> {
    return this.http.put<Cat>(`${this.API_URL}/list/update-cat/${id}`, cat);
  }
  deleteCat(id?: number) :Observable<Cat>{
    return this.http.delete<Cat>(`${this.API_URL}/list/delete-cat/${id}`)
  }
  deleteFact(id?: number) :Observable<CatFact>{
    return this.http.delete<CatFact>(`${this.API_URL}/list/delete-fact/${id}`)
  }
  deleteCatToOwner(catId?: number, ownerId?: number) :Observable<Owner>{
    return this.http.delete<Owner>(`${this.API_URL}/owner/cat/${catId}/owner/${ownerId}`);
  }
}
