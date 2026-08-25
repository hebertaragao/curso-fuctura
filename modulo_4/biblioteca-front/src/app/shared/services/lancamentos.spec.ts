import { TestBed } from '@angular/core/testing';

import { Lancamentos } from './lancamentos';

describe('Lancamentos', () => {
  let service: Lancamentos;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Lancamentos);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
