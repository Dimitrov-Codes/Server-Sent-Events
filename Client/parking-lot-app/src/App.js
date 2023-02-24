import './App.css';
import Header from "./components/Layout/Header";
import DataContainer from "./components/ParkingData/DataContainer";
import {useEffect, useState} from "react";
import DUMMY_DATA from "./store/DummyData";

function App() {
    const [parkingData, setParkingData] = useState(DUMMY_DATA);
    useEffect(() => {
            const source = new EventSource("/getParkingData");
            /*source.onmessage(event => {
                // setParkingData(event.data);
                console.log(event.data);
            });*/
            return () => source.close();
        }, [])

    return (
        <div className="App">
            <Header/>
            <DataContainer data ={parkingData}/>
        </div>
    );
}

export default App;
