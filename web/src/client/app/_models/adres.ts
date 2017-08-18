import {Il} from "./il";
import {Ulke} from "./ulke";

export class Adres {
  id: string;
  acikAdres: string;
  il: Il;
  ulke: Ulke;
  deleted: boolean;

  constructor(id: string,
              acikAdres: string,
              il: Il,
              ulke: Ulke,
              deleted: boolean) {
    this.id = id;
    this.acikAdres = acikAdres;
    this.il = il;
    this.ulke = ulke;
    this.deleted = deleted;

  }
}
