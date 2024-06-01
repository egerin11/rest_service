import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllCatsComponent } from './get-all-cats.component';

describe('GetAllCatsComponent', () => {
  let component: GetAllCatsComponent;
  let fixture: ComponentFixture<GetAllCatsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetAllCatsComponent]
    });
    fixture = TestBed.createComponent(GetAllCatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
