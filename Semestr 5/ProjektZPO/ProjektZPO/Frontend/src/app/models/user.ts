export interface user{
    id: number,
    username: string,
    password: string,
    role: number,
    pc: string
}

export interface mod{
    id: number,
    username: string,
    password: string,
    role: number
}

export enum Role{
    user,
    mod,
    admin
}