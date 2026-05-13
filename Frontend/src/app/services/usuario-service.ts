import { Injectable, inject } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Usuario } from '../models/usuario'
import { environment } from '../../environments/environment.development';

@Injectable({
    providedIn: 'root'
})

export class UsuarioService {
    private http = inject(HttpClient); // Opcion moderna para inyectar


    getUsuarios(): Observable<Usuario[]> {
        return this.http.get<Usuario[]>(environment.apiUrlUsuario);
    }
}