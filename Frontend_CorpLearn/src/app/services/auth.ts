import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private http = inject(HttpClient);

  // Registrar un nuevo usuario
  registrar(usuario: any): Observable<any> {
    return this.http.post(environment.urlRegister, usuario);
  }

  // Autenticar usuario y guardar sesión
  login(credenciales: { email: string; password: string }): Observable<any> {
    return this.http.post<any>(environment.urlLogin, credenciales).pipe(
      tap(response => {
        // MODIFICACIÓN: Extraigo los datos de 'response.data' que viene del BaseController
        const data = response?.data;
        
        if (data && data.token) {
          localStorage.setItem('token', data.token);
          localStorage.setItem('usuarioId', data.usuarioId || '1'); 
          localStorage.setItem('nombre', data.nombre || 'Usuario');
          
          // Opcional: Si necesito guardar el rol para los paneles corporativos, debo descomentar esta línea:
          // localStorage.setItem('rol', data.rol || 'ESTUDIANTE');
        }
      })
    );
  }

  // Métodos de utilidad para el estado de la sesión
  obtenerToken(): string | null {
    return localStorage.getItem('token');
  }

  obtenerUsuarioId(): number {
    const id = localStorage.getItem('usuarioId');
    return id ? parseInt(id, 10) : 0;
  }

  estaLogueado(): boolean {
    return !!this.obtenerToken();
  }

  cerrarSesion(): void {
    localStorage.clear();
  }
}
