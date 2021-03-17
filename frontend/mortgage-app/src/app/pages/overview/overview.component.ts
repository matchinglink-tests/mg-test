import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {MortgageSummary} from "../../data/mortgage-summary";
import {Mortgage} from "../../data/mortgage";
import {MortgageService} from "../../data/mortgage.service";

@Component({
  templateUrl: 'overview.component.html',
  styleUrls: ['overview.component.scss']
})
export class OverviewComponent implements OnInit {

  mortgageList: MortgageSummary[] = [];
  selectedMortgage: Mortgage | null = null

  constructor(private mortgageService: MortgageService, private router: Router) {
  }

  ngOnInit() {
    this.subscribeToMortgageService();
    this.mortgageService.getMortgageList();
  }

  subscribeToMortgageService() {
    this.mortgageService.mortgageSummaryChannel.subscribe((list: MortgageSummary[]) => {
      this.mortgageList = list;
    });

    this.mortgageService.mortgageChannel.subscribe((mortgage: Mortgage) => {
      this.selectedMortgage = mortgage;
    });
  }

  showMortgageDetails(mortgage: MortgageSummary) {
    this.mortgageService.getMortgage(mortgage.identifier)
  }

  navigateToCashFlow() {
    this.router.navigateByUrl('/cashflow/' + this.selectedMortgage?.identifier);
  }
}
