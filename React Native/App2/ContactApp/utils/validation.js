import * as yup from 'yup';

export const contactSchema = yup.object().shape({
  name: yup
    .string()
    .required('Name is required')
    .min(2, 'Name must be at least 2 characters')
    .max(50, 'Name must not exceed 50 characters'),
  mobile: yup
    .string()
    .matches(/^\d+$/, 'Mobile number must contain only digits')
    .matches(/^\d{10}$/, 'Mobile number must be exactly 10 digits')
    .required('Mobile number is required'),
  telephone: yup
    .string()
    .matches(/^\d+$/, 'Telephone number must contain only digits')
    .matches(/^\d{7,15}$/, 'Telephone number must be between 7 and 15 digits'),
});
