import { SchoolTeamsComponent } from './school-teams/school-teams.component';
import { UniTeamsComponent } from './uni-teams/uni-teams.component';
import { ClubTeamsComponent } from './club-teams/club-teams.component';
import { MatchHistoryComponent } from './match-history/match-history.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'match-history', component: MatchHistoryComponent},
  { path: 'club-teams', component: ClubTeamsComponent},
  { path: 'uni-teams', component: UniTeamsComponent},
  { path: 'school-teams', component: SchoolTeamsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const RoutingComponents = [MatchHistoryComponent, ClubTeamsComponent, SchoolTeamsComponent, UniTeamsComponent]
