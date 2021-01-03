import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniClubTableComponent } from './uni-club-table.component';

describe('UniClubTableComponent', () => {
  let component: UniClubTableComponent;
  let fixture: ComponentFixture<UniClubTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UniClubTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UniClubTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
