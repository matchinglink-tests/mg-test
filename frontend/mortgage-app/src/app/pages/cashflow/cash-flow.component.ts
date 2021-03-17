import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {MortgageService} from "../../data/mortgage.service";
import {CashFlowItem} from "../../data/cash-flow-item";

@Component({
  templateUrl: 'cash-flow.component.html',
  styleUrls: ['cash-flow.component.scss']
})
export class CashFlowComponent implements OnInit {
  mortgageIdentifier: string = '';
  cashFlow: CashFlowItem[] = [];

  constructor(private activatedRoute: ActivatedRoute,
              private mortgageService: MortgageService,
              private router: Router){
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe( params => {
      this.mortgageIdentifier = params['id'];
      this.getCashFlow(this.mortgageIdentifier);
      this.mortgageService.getCashFlow(this.mortgageIdentifier);
    })
  }

  getCashFlow(identifier: string) {
    // @ts-ignore
    this.mortgageService.getCashFlow(identifier).subscribe((json: any[]) => {
        this.cashFlow = json.map(item => new CashFlowItem(item));
      },
      (error) => {
        console.error('Failed to load cash flow information for identifier ' + identifier);
        console.error(error);
        this.navigateToOverview();
      });
  }

  navigateToOverview() {
    this.router.navigateByUrl('/');
  }

}
