import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login-component/login-component';
import { ListUsuario } from './pages/list-usuario/list-usuario';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginComponent,
    },
    {
        path: 'list',
        component: ListUsuario,
    }
];
