import { DefaultFootballClub } from './DefaultFootballClub';
import { MatchDate } from './MatchDate';

export interface FootballMatch {
    footballClub1: DefaultFootballClub;
    fc1GoalCount: number;
    footballClub2: DefaultFootballClub;
    fc2GoalCount: number;
    date: MatchDate;
    jsDate ?: Date;
  }
  