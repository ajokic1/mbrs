import { useState, useCallback, useEffect } from 'react';
import axios from 'axios';

export function useData(url) {
  const [data, setData] = useState([]);
  
  const fetchData = useCallback(async () => {
    const result = await axios(url);
    setData(result.data);
    console.log("Loaded data from " + url);
  },[url]);
  
  useEffect(() => {
    if(!url) return;
    fetchData();
  }, [url, fetchData]);
  
  return [data, fetchData];  
}

export async function getData(url, onComplete) {
  const response = await axios(url);
  onComplete(response);
}

export async function submitForm(url, data, onComplete, update) {
  const response = await (update ? axios.patch(url, data) : axios.post(url, data));
  onComplete(response);

}

export async function deleteItem(url, onComplete) {
  const response = await axios.delete(url);
  onComplete(response);
}
