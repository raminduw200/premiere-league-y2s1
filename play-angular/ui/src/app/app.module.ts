import { ApiClubService } from './services/api-club.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule, RoutingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { MatchCardComponent } from './match-history/match-card/match-card.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { HttpClientModule } from '@angular/common/http';
import { ClubTableComponent } from './club-teams/club-table/club-table.component';
import { UniTeamsComponent } from './uni-teams/uni-teams.component';
import { SchoolTeamsComponent } from './school-teams/school-teams.component';
import { SchoolClubTableComponent } from './school-teams/school-club-table/school-club-table.component';
import { UniClubTableComponent } from './uni-teams/uni-club-table/uni-club-table.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {FormsModule} from '@angular/forms';
import { MatNativeDateModule } from '@angular/material/core';
import {MatIconModule} from '@angular/material/icon';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    RoutingComponents,
    MatchCardComponent,
    ClubTableComponent,
    UniTeamsComponent,
    SchoolTeamsComponent,
    SchoolClubTableComponent,
    UniClubTableComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    HttpClientModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatInputModule,
    FormsModule,
    MatNativeDateModule,
    MatIconModule
  ],
  providers: [ApiClubService],
  bootstrap: [AppComponent]
})
export class AppModule { }
