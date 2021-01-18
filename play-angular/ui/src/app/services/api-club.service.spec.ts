import { TestBed } from '@angular/core/testing';

import { ApiClubService } from './api-club.service';

describe('ApiClubService', () => {
  let service: ApiClubService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiClubService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
