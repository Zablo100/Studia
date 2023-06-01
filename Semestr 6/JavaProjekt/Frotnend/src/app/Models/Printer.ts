import { Department } from "./Department";


export interface Printer {
    id: number;
    model: string;
    manufacturer: string;
    description: string | null;
    invoiceDescription: string | null;
    contractNumber: string | null;
    iP: string | null;
    test: boolean;
    department: Department;
    serialNumber: string;
}