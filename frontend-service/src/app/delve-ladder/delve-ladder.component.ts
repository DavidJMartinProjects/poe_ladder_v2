import { LeaderboardService } from "./../../services/leaderboard-service.service";
import { Component, OnInit, OnDestroy, ChangeDetectorRef } from "@angular/core";
import { Subject } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { LeaderboardModel } from 'src/app/models/LeaderboardModel';

declare var $;

@Component({
  selector: "app-ladder-group-delve",
  templateUrl: "./ladder-group-delve.component.html",
  styleUrls: ["./ladder-group-delve.component.css"]
})
export class DelveLadderComponent implements OnInit, OnDestroy {
  ngOnDestroy(){
    this.subscription.unsubscribe();
    clearInterval(this.interval);
  }
  subscription: any;
  league: string;

  delveLeaderboard = new Array<LeaderboardModel>();
  softcore = new Array<LeaderboardModel>();
  hardcore = new Array<LeaderboardModel>();
  softcoreSsf = new Array<LeaderboardModel>();
  hardcoreSsf = new Array<LeaderboardModel>();

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  changeDetectorRef: ChangeDetectorRef;
  interval:any;

  constructor(private leaderboardService: LeaderboardService, private activatedRoute: ActivatedRoute, private router: Router, changeDetectorRef: ChangeDetectorRef) {
  }

  ngOnInit() {
    this.league = this.activatedRoute.snapshot.paramMap.get("leagueName");
    console.log("Loading Delve ranks for " + this.league + " league.");
    this.leaderboardService = this.leaderboardService;
    this.changeDetectorRef = this.changeDetectorRef;

    this.refreshData();
    this.interval = setInterval(() => {
        this.refreshData();
    }, 600000); 
  }

  refreshData() {
    this.subscription = this.activatedRoute.params.subscribe(params => {
      this.league = params['league']; // (+) converts string 'id' to a number
      console.log("Refreshing Delve ranks for " + this.league + " league.");

      this.delveLeaderboard = new Array<LeaderboardModel>();
      this.softcore = new Array<LeaderboardModel>();
      this.hardcore = new Array<LeaderboardModel>();
      this.softcoreSsf = new Array<LeaderboardModel>();
      this.hardcoreSsf = new Array<LeaderboardModel>();

      this.subscription = this.leaderboardService.getDelveLeaderboards(this.league).subscribe(response => {
        this.delveLeaderboard = response.map(item => {
          return new LeaderboardModel(
            item.rank,
            item.rankDifference,
            item.account,
            item.character,
            item.ascendancy,
            item.league,
            item.leaderboard,
            item.level,
            item.depth,
            item.time,
            item.timeFormatted,
            item.experience,  
            item.experienceDifference,          
            item.progress,
            item.online,
            item.dead, 
            item.timeStamp
          );
        });

        for (var i = 0; i < this.delveLeaderboard.length; i++) {
          if (this.delveLeaderboard[i].league.includes("HC") && !this.delveLeaderboard[i].league.includes("SSF") || this.delveLeaderboard[i].league.includes("Hardcore") && !this.delveLeaderboard[i].league.includes("SSF")) {
            this.hardcore.push(this.delveLeaderboard[i]);
          } else if (this.delveLeaderboard[i].league.includes("HC") && this.delveLeaderboard[i].league.includes("SSF") || this.delveLeaderboard[i].league.includes("Hardcore") && this.delveLeaderboard[i].league.includes("SSF")) {
            this.hardcoreSsf.push(this.delveLeaderboard[i]);
          } else if (!this.delveLeaderboard[i].league.includes("SSF") || this.delveLeaderboard[i].league.includes("Standard") && !this.delveLeaderboard[i].league.includes("SSF")) {
            this.softcore.push(this.delveLeaderboard[i]);
          } else if (this.delveLeaderboard[i].league.includes("SSF")) {
            this.softcoreSsf.push(this.delveLeaderboard[i]);
          }
        }
      });
   });

  }
  onClick(league: string, leaderboard: string) {
    this.router.navigate(['ladder/top-100/', league, leaderboard]);
    console.log('onClick /top-100/'+ leaderboard +'/'+ league);
  }

}
