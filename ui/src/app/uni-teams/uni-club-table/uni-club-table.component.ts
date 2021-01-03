import { UniversityFootballClub } from './../../entities/UniversityFootballClub';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { ApiClubService } from 'src/app/services/api-club.service';

@Component({
  selector: 'app-uni-club-table',
  templateUrl: './uni-club-table.component.html',
  styleUrls: ['./uni-club-table.component.css']
})
export class UniClubTableComponent {

  CLUB_DATA!: UniversityFootballClub[];
  columnNames: string[] = ['clubName','uniName','winCount','drawCount','defeatCount','matchCount', 'goalScoredCount','goalReceivedCount', 'goalDifference', 'points'];
  tableData = new MatTableDataSource<UniversityFootballClub>(this.CLUB_DATA);

  @ViewChild(MatSort, {static: true}) sort!: MatSort;
  constructor(private apiClubService:ApiClubService) { }

  ngOnInit() {
    this.tableData.sort=this.sort;
    this.getAllUniClubs();
  }

  public getAllUniClubs(){
    this.apiClubService.getAllUniClubs().subscribe(clubs=>this.tableData.data=clubs  as UniversityFootballClub[])
  }

  search(event: Event) {
    // https://stackoverflow.com/questions/42066421/property-value-does-not-exist-on-type-eventtarget
    this.tableData.filter = (<HTMLInputElement>event.target).value.trim().toLowerCase();
  }
}
