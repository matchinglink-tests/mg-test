export class Mortgage {
  clientCode: string;
  portfolioCode: string;
  identifier: string;
  originalNotional: number;
  type: string;
  currentNotional: number;
  startDate: Date;
  endDate: Date;
  interestRate: number;
  fixedUntil: Date;
  currentMonthlyInterest: number;
  currentMonthlyAnnuity: number;
  currentLinearRepayment: number;
  currentPolicyPremium: number;
  propertyValue: number;
  propertyValueDate: Date;
  properLiquidationValue: number;
  policyAndOtherValue: number;
  postalCode: string;
  isNhg: boolean;

  constructor(json: any) {
    this.clientCode = json.clientCode;
    this.portfolioCode = json.portfolioCode;
    this.identifier = json.identifier;
    this.originalNotional = json.originalNotional;
    this.type = json.type;
    this.currentNotional = json.currentNotional;
    this.startDate = json.startDate;
    this.endDate = json.endDate;
    this.interestRate = json.interestRate;
    this.fixedUntil = json.fixedUntil;
    this.currentMonthlyInterest = json.currentMonthlyInterest;
    this.currentMonthlyAnnuity = json.currentMonthlyAnnuity;
    this.currentLinearRepayment = json.currentLinearRepayment;
    this.currentPolicyPremium = json.currentPolicyPremium;
    this.propertyValue = json.propertyValue;
    this.propertyValueDate = json.propertyValueDate;
    this.properLiquidationValue = json.properLiquidationValue;
    this.policyAndOtherValue = json.policyAndOtherValue;
    this.postalCode = json.postalCode;
    this.isNhg = json.isNhg;
  }

}
