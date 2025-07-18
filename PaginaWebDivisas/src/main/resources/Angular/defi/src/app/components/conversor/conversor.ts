import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

// Define the Divisa interface
export interface Divisa {
  id: string;
  nombre: string;
  compra: number;
  venta: number;
}

@Component({
  selector: 'app-conversor',
  imports: [CommonModule, FormsModule],
  templateUrl: './conversor.html',
  styleUrl: './conversor.css'
})

export class Conversor {
  @Input() divisas: Divisa[] = [];

  divisaOrigen: string = 'ARS';
  divisaDestino: string = 'ARS';
  cantidad!: number;
  resultado: string = '';

  convertirDivisa() {
    if (!this.cantidad || this.cantidad <= 0) {
      this.resultado = 'Por favor, ingresa una cantidad válida mayor a cero.';
      return;
    }

    const divisaOrigen = this.divisas.find(d => d.id === this.divisaOrigen) || { compra: 1, nombre: "ARS" };
    const divisaDestino = this.divisas.find(d => d.id === this.divisaDestino) || { venta: 1, nombre: "ARS" };

    let resultadoNum = 0;

    if (this.divisaOrigen === this.divisaDestino) {
      resultadoNum = this.cantidad;
    } else if (this.divisaOrigen === 'ARS') {
      resultadoNum = this.cantidad / divisaDestino.venta;
    } else if (this.divisaDestino === 'ARS') {
      resultadoNum = this.cantidad * divisaOrigen.compra;
    } else {
      const aPesos = this.cantidad * divisaOrigen.compra;
      resultadoNum = aPesos / divisaDestino.venta;
    }

    this.resultado = `Resultado: ${resultadoNum.toFixed(2)} ${divisaDestino.nombre}`;
  }
}
