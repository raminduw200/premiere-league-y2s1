import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ApiClubService {
  private listOfDefaultFootballClubs = '/api/defaultFootballClubs';
  private listOfUniFootballClubs = '/api/uniFootballClubs';
  private listOfSchoolFootballClubs = '/api/schoolFootballClubs';
  private listOfMatches = '/api/footballMatches';
  private createMatchApi = '/api/createMatch';

  constructor(private http:HttpClient) { }

  public getAllDefaultClubs() {
    return this.http.get(this.listOfDefaultFootballClubs);
  }

  public getAllUniClubs() {
    return this.http.get(this.listOfUniFootballClubs);
  }

  public getAllSchoolClubs() {
    return this.http.get(this.listOfSchoolFootballClubs);
  }

  public getAllMatches(): Observable<object[]> {
    return this.http.get<Object[]>(this.listOfMatches);
  }

  public createMatch() {
    return this.http.get(this.createMatchApi);
  }
}
