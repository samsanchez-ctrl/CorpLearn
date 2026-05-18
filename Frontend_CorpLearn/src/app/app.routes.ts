import { Routes } from '@angular/router';

export const routes: Routes = [
   { 
    path: 'cursos', 
    loadComponent: () => import('./pages/cursos/cursos').then(m => m.CursosComponent) 
  },
  { 
    path: 'login', 
    loadComponent: () => import('./pages/login/login').then(m => m.LoginComponent)
  },
  { 
    path: 'register', 
    loadComponent: () => import('./pages/register/register').then(m => m.RegisterComponent) 
  },
  { 
    path: '', 
    redirectTo: 'login', 
    pathMatch: 'full' 
  }, // Si entra a la raíz, lo manda al login
  { 
    path: '**', 
    redirectTo: 'login' 
  } // Si escribe cualquier cosa en la URL, lo redirige al login
];
