import {createTheme, FormControl, InputLabel, MenuItem, Select, ThemeProvider} from "@mui/material";
import {useState} from "react";
import ParkingGrid from "./ParkingGrid";
import classes from "./DataContainer.module.css"
import {makeStyles} from "@mui/styles";

const useStyles = makeStyles((theme) => ({
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
    },
}));

export default function DataContainer(props) {
    const theme = createTheme({
        components: {
            MuiOutlinedInput: {
                styleOverrides: {
                    root: {
                        '& fieldset': {
                            borderColor: 'white',
                        },
                        '&:hover fieldset': {
                            borderColor: 'green',
                        },
                    },
                },
            },
        },
        palette: {
            primary: {
                main: '#FFF',
                dark: "#FFF"
            },
            secondary:
                {
                    main: '#3473b3',
                },
        }
    })
    const [floor, setFloor] = useState("64efb11a-0c20-4f23-9e47-181d9b2f1cc8");
    const [parkingSpots, setParkingSpots] = useState(props.data[0].parkingData)
    const handleFloorChange = (event) => {
        const floorID = event.target.value
        console.log(floorID);
        setFloor(floorID);
        setParkingSpots(props.data.find(key => key.id === floorID).parkingData)
    }

    return (
        <ThemeProvider theme={theme}>
            <FormControl className={classes.data}>
                <InputLabel color="secondary" sx={{color: "#FFF"}} id={"floor"}>Floor</InputLabel>
                <Select
                    id={"floorSelect"}
                    value={floor}
                    variant={"outlined"}
                    sx={{marginBottom: 1, color: "#FFF"}}
                    labelId={"floor"}
                    label={"Floor"}
                    color="secondary"
                    onChange={handleFloorChange}
                    style={{width: "25%"}}
                    defaultValue={floor}>
                    {props.data.map((floorData, index) => <MenuItem key={index}
                                                                    value={floorData.id}> {floorData.floor} </MenuItem>)}
                </Select>
                <ParkingGrid parkingSpots={parkingSpots}/>
            </FormControl>
        </ThemeProvider>
    )
}