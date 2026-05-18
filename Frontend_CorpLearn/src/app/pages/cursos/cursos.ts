import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CursoService } from '../../services/curso';
import { InscripcionService } from '../../services/inscripcion';
import { NotificacionService } from '../../services/notificacion';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-cursos',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cursos.html',
  styleUrl: './cursos.css'
})
export class CursosComponent implements OnInit {
  // Inyección de dependencias de todos nuestros servicios
  private cursoService = inject(CursoService);
  private inscripcionService = inject(InscripcionService);
  private notificacionService = inject(NotificacionService);
  private authService = inject(AuthService);
  private router = inject(Router);

  // Variables de Estado
  cursos: any[] = [];
  inscripciones: any[] = [];
  notificaciones: any[] = [];
  
  usuarioId: number = 0;
  nombreUsuario: string = 'Estudiante';
  mostrarNotificaciones: boolean = false;

  ngOnInit(): void {
    // Validamos que el usuario esté logueado, sino lo mandamos al login
    if (!this.authService.estaLogueado()) {
      this.router.navigate(['/login']);
      return;
    }

    this.usuarioId = this.authService.obtenerUsuarioId();
    this.nombreUsuario = localStorage.getItem('nombre') || 'Estudiante';

    this.cargarDatos();
  }

  cargarDatos(): void {
    // 1. Cargar catálogo de cursos
    this.cursoService.listarCursos().subscribe({
      next: (res) => this.cursos = res,
      error: (err) => console.error('Error al cargar cursos', err)
    });

    // 2. Cargar inscripciones existentes del alumno
    this.inscripcionService.listarPorUsuario(this.usuarioId).subscribe({
      next: (res) => this.inscripciones = res,
      error: (err) => console.error('Error al cargar inscripciones', err)
    });

    // 3. Cargar alertas no leídas
    this.notificacionService.obtenerNoLeidas(this.usuarioId).subscribe({
      next: (res) => this.notificaciones = res,
      error: (err) => console.error('Error al cargar notificaciones', err)
    });
  }

  // Verificar si el alumno ya se encuentra inscrito en una materia
  esInscrito(cursoId: number): boolean {
    return this.inscripciones.some(ins => ins.cursoId === cursoId);
  }

  // Acción: Inscribir al alumno en un curso y disparar alerta asíncrona
  inscribirse(cursoId: number, nombreCurso: string): void {
    this.inscripcionService.inscribir(this.usuarioId, cursoId).subscribe({
      next: () => {
        // Al inscribirse con éxito, generamos la notificación en el backend automático
        const mensajeAlerta = `Te has inscrito exitosamente al curso: ${nombreCurso}.`;
        
        this.notificacionService.crearAlerta(this.usuarioId, mensajeAlerta).subscribe({
          next: () => this.cargarDatos() // Recargamos la interfaz para actualizar estados y contador de campana
        });
      },
      error: (err) => alert('No se pudo procesar la inscripción en este momento.')
    });
  }

  // Acción: Marcar una alerta como leída
  leerNotificacion(id: number): void {
    this.notificacionService.marcarComoLeida(id).subscribe({
      next: () => {
        // Removemos de la lista local de pendientes
        this.notificaciones = this.notificaciones.filter(notif => notif.id !== id);
      }
    });
  }

  toggleNotificaciones(): void {
    this.mostrarNotificaciones = !this.mostrarNotificaciones;
  }

  salir(): void {
    this.authService.cerrarSesion();
    this.router.navigate(['/login']);
  }
}