import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SchoolClubTableComponent } from './school-club-table.component';

describe('SchoolClubTableComponent', () => {
  let component: SchoolClubTableComponent;
  let fixture: ComponentFixture<SchoolClubTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SchoolClubTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SchoolClubTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
