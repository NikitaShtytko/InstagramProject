import {TestBed} from '@angular/core/testing';

import {LikeeService} from './likee.service';

describe('LikeeService', () => {
  let service: LikeeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LikeeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
