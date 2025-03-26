import './App.css';
import {
    AlertDialog,
    Button,
    ButtonGroup,
    defaultTheme,
    DialogContainer, Form,
    Provider, Text, TextField
} from "@adobe/react-spectrum";
import {useState} from "react";
import axios from 'axios';


const App = () => {

    const [inputNumber, setInputNumber] = useState(1);
    const [romanNumeral, setRomanNumeral] = useState("");
    const [error, setError] = useState("");
    const [alertDialog, setAlertDialog] = useState(false);
    const [alertMessage, setAlertMessage] = useState("");
    const [disableSubmit, setDisableSubmit] = useState(false);

    const onInputChange = (value) => {
        setRomanNumeral("");
        setDisableSubmit(false);
        setError("")
        if (value < 1 || value > 3999 || isNaN(value) || value === "") {
            setError(`Invalid Input Entry: ${(value === "") ? "Empty value" : value}; only values from 1 to 3999 is allowed`)
            setDisableSubmit(true);
        }
        setInputNumber(value);
    }

    const convertNumber = (event) => {
        event.preventDefault();
        requestApi();
    }

    const requestApi = async () => {
        try {
            const response = await axios.get('http://localhost:8080/romannumeral?query=' + inputNumber, {
                setTimeout: 5000,
                withCredentials: true
            });
            setRomanNumeral(response.data.output)
        } catch (error) {
            console.log(error.message);
            setAlertMessage(error.message);
            setAlertDialog(true);
        }
    }
    const cancelAlert = () => {
        setAlertMessage("");
        setAlertDialog(false);
    }

    return (
        <div className={"App"}>
            <Provider theme={defaultTheme} height={"100%"} width={"100%"} position={"absolute"}>
                {alertDialog && (<DialogContainer>
                    <AlertDialog variant={"error"}
                                 title={"Error"}
                                 primaryActionLabel={"Ok"}
                                 autoFocusButton={"primary"}
                                 onPrimaryAction={cancelAlert}>{alertMessage}</AlertDialog>
                </DialogContainer>)}
                <Form onSubmit={convertNumber} margin={"0 15%"}>
                    <h1 style={{marginTop: "10%"}}>Roman numeral converter</h1>
                    <TextField label="Enter a number"
                               name="number"
                               value={inputNumber}
                               validationState={error ? 'invalid' : undefined}
                               errorMessage={error}
                               onChange={onInputChange}/>
                    <ButtonGroup position={"relative"}
                                 width={"100%"} marginTop={"20px"}
                                 marginBottom={"20px"}
                                 isDisabled={disableSubmit}>
                        <Button type="submit" variant="primary">Convert to roman numeral</Button>
                    </ButtonGroup>
                    <Text marginBottom={"10%"}><b>Roman numeral</b>: {romanNumeral}</Text>
                </Form>
            </Provider>
        </div>
    );
}

export default App;
