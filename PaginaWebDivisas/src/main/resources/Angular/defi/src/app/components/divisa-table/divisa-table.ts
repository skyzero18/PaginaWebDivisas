import { Component } from '@angular/core';
import { Api } from '../../services/api';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-divisa-table',
  imports: [CommonModule],
  templateUrl: './divisa-table.html',
  styleUrl: './divisa-table.css'
})

export class DivisaTable {
  divisas: any[] = [];
  cargando = false;

  constructor(private api: Api) {}

  ngOnInit() {
    this.cargando = true;
    this.api.get<any[]>('divisas').subscribe({
      next: (data) => {
        // Solo divisas activas
        this.divisas = data.filter(d => d.status === true);
        this.cargando = false;
      },
      error: (e) => {
        console.error('Error al obtener divisas:', e);
        this.cargando = false;
      }
    });
  }
}
