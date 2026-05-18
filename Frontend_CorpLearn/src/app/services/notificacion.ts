import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NotificacionService {
  private http = inject(HttpClient);

  // Crear una notificación de forma manual (por ejemplo, al inscribirse)
  crearAlerta(usuarioId: number, mensaje: string): Observable<any> {
    const body = { usuarioId, mensaje };
    return this.http.post<any>(environment.urlNotificacion, body);
  }

  // Traer todas las notificaciones del alumno
  obtenerHistorial(usuarioId: number): Observable<any[]> {
    return this.http.get<any[]>(`${environment.urlNotificacion}/usuario/${usuarioId}`);
  }

  // Traer únicamente las alertas sin leer (para el contador flotante)
  obtenerNoLeidas(usuarioId: number): Observable<any[]> {
    return this.http.get<any[]>(`${environment.urlNotificacion}/usuario/${usuarioId}/no-leidas`);
  }

  // Marcar una alerta específica como leída
  marcarComoLeida(id: number): Observable<any> {
    return this.http.put<any>(`${environment.urlNotificacion}/${id}/leer`, {});
  }

  // Borrar una notificación de la vista del usuario
  eliminarAlerta(id: number): Observable<any> {
    return this.http.delete<any>(`${environment.urlNotificacion}/${id}`);
  }
}
