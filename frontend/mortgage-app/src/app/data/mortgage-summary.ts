export class MortgageSummary {
  clientCode: string = '';
  portfolioCode: string = '';
  identifier: string = '';
  type: string = '';

  static fromJson(json: any) : MortgageSummary {
    const summary = new MortgageSummary();
    summary.clientCode = json.clientCode;
    summary.portfolioCode = json.portfolioCode;
    summary.identifier = json.identifier;
    summary.type = json.type;
    return summary;
  }
}
