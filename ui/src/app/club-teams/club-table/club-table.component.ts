import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { ApiClubService } from 'src/app/services/api-club.service';
import { DefaultFootballClub } from './../../entities/DefaultFootballClub'

@Component({
  selector: 'app-club-table',
  templateUrl: './club-table.component.html',
  styleUrls: ['./club-table.component.css']
})

export class ClubTableComponent implements OnInit {

  CLUB_DATA!: DefaultFootballClub[];
  columnNames: string[] = ['clubName','location','winCount','drawCount','defeatCount','matchCount', 'goalScoredCount','goalReceivedCount', 'goalDifference', 'points'];
  tableData = new MatTableDataSource<DefaultFootballClub>(this.CLUB_DATA);

  @ViewChild(MatSort, {static: true}) sort!: MatSort;
  constructor(private apiClubService:ApiClubService) { }

  ngOnInit() {
    this.tableData.sort=this.sort;
    this.getAllDefaultClubs();
  }

  public getAllDefaultClubs(){
    this.apiClubService.getAllDefaultClubs().subscribe(clubs=>this.tableData.data=clubs  as DefaultFootballClub[])
  }

  search(event: Event) {
    // https://stackoverflow.com/questions/42066421/property-value-does-not-exist-on-type-eventtarget
    this.tableData.filter = (<HTMLInputElement>event.target).value.trim().toLowerCase();
  }
}
