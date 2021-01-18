import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClubTeamsComponent } from './club-teams.component';

describe('ClubTeamsComponent', () => {
  let component: ClubTeamsComponent;
  let fixture: ComponentFixture<ClubTeamsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClubTeamsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClubTeamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
