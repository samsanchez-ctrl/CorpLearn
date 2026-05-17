import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Usuario } from '../../models/usuario';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  public listaUsuarios: Usuario[] = [];

  constructor(private _userService: UserService) { }

  ngOnInit(): void {
    this.cargarUsuarios();
  }

  async cargarUsuarios() {
    try {
      this.listaUsuarios = await this._userService.getUsers();
    } catch (error) {
      console.error("Error al cargar usuarios:", error);
    }
  }

  async eliminar(id: number | undefined) {
    if (!id) return;

    if (confirm('¿Deseas eliminar este usuario?')) {
      try {
        await this._userService.deleteUser(id);
        this.cargarUsuarios(); // Refrescar lista
      } catch (error) {
        alert("Error al eliminar");
      }
    }
  }
}
