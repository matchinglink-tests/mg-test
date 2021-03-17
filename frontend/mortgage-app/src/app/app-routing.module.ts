import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {OverviewComponent} from "./pages/overview/overview.component";
import {CashFlowComponent} from "./pages/cashflow/cash-flow.component";

const routes: Routes = [
  { path: '', component: OverviewComponent },
  { path: 'cashflow/:id', component: CashFlowComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
