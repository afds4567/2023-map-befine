import { useContext } from 'react';
import ToastProps from '../types/Toast';
import { ToastContext } from '../context/ToastContext';
import { TOAST_SHOWTIME } from '../constants';

let timeoutID: null | number = null;

const useToast = () => {
  const { setToast } = useContext(ToastContext);

  const showToast = (
    type: ToastProps['type'],
    message: ToastProps['message'],
  ) => {
    if (timeoutID) clearTimeout(timeoutID);

    setToast({ show: true, type, message });

    timeoutID = window.setTimeout(() => {
      setToast({ show: false, type, message: '' });
    }, TOAST_SHOWTIME);
  };

  return { showToast };
};

export default useToast;
