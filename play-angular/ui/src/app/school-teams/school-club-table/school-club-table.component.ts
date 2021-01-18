import { SchoolFootballClub } from './../../entities/SchoolFootballClub';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { ApiClubService } from 'src/app/services/api-club.service';

@Component({
  selector: 'app-school-club-table',
  templateUrl: './school-club-table.component.html',
  styleUrls: ['./school-club-table.component.css']
})
export class SchoolClubTableComponent {

  CLUB_DATA!: SchoolFootballClub[];
  columnNames: string[] = ['clubName','schoolName','winCount','drawCount','defeatCount','matchCount', 'goalScoredCount','goalReceivedCount', 'goalDifference', 'points'];
  tableData = new MatTableDataSource<SchoolFootballClub>(this.CLUB_DATA);

  @ViewChild(MatSort, {static: true}) sort!: MatSort;
  constructor(private apiClubService:ApiClubService) { }

  ngOnInit() {
    this.tableData.sort=this.sort;
    this.getAllSchoolClubs();
  }

  public getAllSchoolClubs(){
    this.apiClubService.getAllSchoolClubs().subscribe(clubs=>this.tableData.data=clubs  as SchoolFootballClub[])
  }

  search(event: Event) {
    // https://stackoverflow.com/questions/42066421/property-value-does-not-exist-on-type-eventtarget
    this.tableData.filter = (<HTMLInputElement>event.target).value.trim().toLowerCase();
  }
  
}
