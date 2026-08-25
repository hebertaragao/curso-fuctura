import { DateTime } from 'luxon';

/**
 * Converte a data para o formato YYYY-MM-DD
 * @param date formato de entrada: YYYY-MM-DDTHH:mm:ss.SSSZ
 * @returns  formato de saída: YYYY-MM-DD
 */
const convertToDateDB = (date: string): string => {
  return  DateTime.fromISO(date).toFormat('YYYY-MM-DD');
}

export default convertToDateDB;