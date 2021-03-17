export class CashFlowItem {
  paymentDate: Date;
  amount: number;

  constructor(json: any) {
    this.paymentDate = json.paymentDate;
    this.amount = json.amount;
  }
}
