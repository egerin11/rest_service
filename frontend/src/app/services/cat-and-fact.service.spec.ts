import { TestBed } from '@angular/core/testing';

import { CatAndFactService } from './cat-and-fact.service';

describe('CatAndFactService', () => {
  let service: CatAndFactService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CatAndFactService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
