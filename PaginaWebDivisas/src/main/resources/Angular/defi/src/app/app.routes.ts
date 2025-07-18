import { Routes } from '@angular/router';
import { Loginpage } from './components/loginpage/loginpage';
import { Homepage } from './components/homepage/homepage';
import { Adminpage } from './components/adminpage/adminpage';
export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'login', component: Loginpage },
  { path: 'home', component: Homepage },
  { path: 'admin', component: Adminpage }
];