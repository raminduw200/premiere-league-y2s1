import { ApiClubService } from 'src/app/services/api-club.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-match-card',
  templateUrl: './match-card.component.html',
  styleUrls: ['./match-card.component.css']
})

export class MatchCardComponent {

  matches:any[] = [];
  searchMatchesCopy:any[] = [];
  date!: any;
  
  constructor(private apiMatchService:ApiClubService) {
    this.getAllMatches();
  }

  ngOnInit(): void {
  }

  public getAllMatches(){
    this.apiMatchService.getAllMatches().subscribe(matches=>{
      this.matches=matches
      this.searchMatchesCopy=matches
      this.calculateDate();
    });
  }

  public createMatch(){
    this.apiMatchService.createMatch().subscribe(match=>{
    this.matches.push(match)
    this.calculateDate();
    });
  }

  public sortByDate(){
    this.matches.sort((a: any,b : any)=>a.jsDate - b.jsDate);
  }

  public onDate(date : Date) {
    this.matches = this.searchMatchesCopy.filter(function(match) {
      return match.jsDate.getTime() == date.getTime();
    });
  }

  public calculateDate(){
    this.matches = this.matches.map (match=>{
      match.jsDate = new Date(`${match.date.year}-${match.date.month}-${match.date.day}`);
      return match;
    })
  }

  public clearDate(event : any) {
    this.date = null;
    this.getAllMatches();
  }
}
