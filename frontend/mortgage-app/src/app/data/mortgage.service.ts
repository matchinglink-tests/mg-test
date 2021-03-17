import {EventEmitter, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MortgageSummary} from "./mortgage-summary";
import {Mortgage} from "./mortgage";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MortgageService {
  private MORTAGE_REST_BASE = 'http://localhost:8080/mortgage';

  mortgageSummaryChannel: EventEmitter<MortgageSummary[]> = new EventEmitter<MortgageSummary[]>();
  mortgageChannel: EventEmitter<Mortgage> = new EventEmitter<Mortgage>();

  constructor(private http: HttpClient) {
  }

  /**
   * Gets the list of mortgage summary's from the back end and emits this list via the mortgageSummaryChannel.
   */
  public getMortgageList() {
    // @ts-ignore
    this.http.get(this.MORTAGE_REST_BASE).subscribe((json: any[]) => {
      const mortgageSummaries = json.map(item => MortgageSummary.fromJson(item));
        this.mortgageSummaryChannel.emit(mortgageSummaries);
    });
  }

  /**
   * Gets the full mortgage details of the mortgage associated with the provided identifier, and emits this information
   * on the mortgageChannel.
   */
  public getMortgage(identifier: string) {
    const url = this.MORTAGE_REST_BASE + '/' + identifier;
    this.http.get(url).subscribe((json: any) => {
      const mortgage = new Mortgage(json);
      this.mortgageChannel.emit(mortgage);
    });
  }

  /**
   * Gets the cash flow information of the mortgage associated with the provided identifier, and returns the Observable
   * with the response from the back end.
   */
  public getCashFlow(identifier: string): Observable<Object> {
    const url = this.MORTAGE_REST_BASE + '/' + identifier + '/cashflow';
    return this.http.get(url);
  }

}
