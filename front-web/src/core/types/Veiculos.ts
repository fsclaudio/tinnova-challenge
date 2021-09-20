export type VeiculoResponse = {
   content: Veiculos[];
   totalPages: number; 
}
export type Veiculos = {
    id: number;
    veiculo: string;
    marca: string;
    ano: number;
    descricao: string;
    vendido: boolean;
}
