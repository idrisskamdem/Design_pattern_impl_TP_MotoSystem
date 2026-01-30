import { useState } from "react";
import { BaseVehicle } from "./BaseVehicle";
import { IVehicle } from "./IVehicle";
import { SiegesCuirDecorator } from "./SiegesCuirDecorator";
import { SiegesSportDecorator } from "./SiegesSportDecorator";
import { GpsDecorator } from "./GpsDecorator";
import { ToitOuvrantDecorator } from "./ToitOuvrantDecorator";
import { CameraReculDecorator } from "./CameraReculDecorator";

export interface Option { 
  code: string;
  nom: string;
  prix: number;
}

export function useDecoratedVehicle(name: string, basePrice: number) {
  const [vehicle, setVehicle] = useState<IVehicle>(
    new BaseVehicle(name, basePrice)
  );
  const [options, setOptions] = useState<Option[]>([]);

  const addOption = (option: Option) => {
    let newVehicle = vehicle;

    //  Vérifier incompatibilités cuir/sport
    if (
      (option.code === "SIEGES_CUIR" && options.find(o => o.code === "SIEGES_SPORT")) ||
      (option.code === "SIEGES_SPORT" && options.find(o => o.code === "SIEGES_CUIR"))
    ) {
      alert("Option incompatible : cuir et sport");
      return;
    }

    //  Vérifier si déjà ajoutée
    if (options.find(o => o.code === option.code)) return;

    //  Appliquer le décorateur
    if (option.code === "SIEGES_CUIR") newVehicle = new SiegesCuirDecorator(vehicle);
    if (option.code === "SIEGES_SPORT") newVehicle = new SiegesSportDecorator(vehicle);
    if (option.code === "GPS") newVehicle = new GpsDecorator(vehicle);
    if (option.code === "TOIT_OUVRANT") newVehicle = new ToitOuvrantDecorator(vehicle);
    if (option.code === "CAMERA_RECUL") newVehicle = new CameraReculDecorator(vehicle);

    setVehicle(newVehicle);
    setOptions([...options, option]);
  };

  const removeOption = (code: string) => {
    setOptions(options.filter(o => o.code !== code));
    // Ici, tu pourrais reconstruire le véhicule décoré sans cette option
    setVehicle(new BaseVehicle(name, basePrice));
    options
      .filter(o => o.code !== code)
      .forEach(o => addOption(o)); // Re-applique les décorateurs restants
  };

  const getDescription = () =>
    name + options.map(o => ` + ${o.nom}`).join("");

  const getPrice = () =>
    basePrice + options.reduce((sum, o) => sum + o.prix, 0);

  return { vehicle, options, addOption, removeOption, getDescription, getPrice };
}
