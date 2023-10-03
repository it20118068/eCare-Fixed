import React, { useEffect, useState } from "react";
import DOMPurify from 'dompurify';
import RestService from "../services/RestService";
import { useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';

function ViewMedicine() {
    const [isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const [token, setToken] = useState(sessionStorage.getItem("token"));
    const [username, setUserName] = useState(sessionStorage.getItem("username"));
    const [userId, setUserId] = useState(sessionStorage.getItem("userId"));
    const { id } = useParams();
    const [medicine, setMedicine] = useState([]);
    const [pharmacyList, setPharmacyList] = useState([]);

    useEffect(() => {
        if (isLogged) {
            const medicineDto = {
                id: id
            }

            const requestDto = {
                medicineDto: medicineDto
            }

            RestService.findMedicineById(token, requestDto).then((res) => {
                setPharmacyList(res.data.pharmacyDtoList);
                setMedicine(res.data.medicineDto);
            }).catch((err) => {
                console.log(err);
            });
        } else {
            window.location.href = '/login';
        }
    }, [])

    const sanitizedDescription = DOMPurify.sanitize(medicine.description);

    return (
        <div style={{ marginBottom: '10vh' }}>
            <h1>{DOMPurify.sanitize(medicine.name)}</h1>
            <hr />
            <div className="row mt-5">
                <div className="col">
                    <div className="text-center">
                        <img src={DOMPurify.sanitize(medicine.image)} alt="Medicine" style={{ width: '75%' }} />
                    </div>
                </div>
                <div className="col">
                    <h6>Common Brand: <span style={{ color: 'blue' }}>{DOMPurify.sanitize(medicine.brand)}</span></h6>
                    <hr />
                    <p className="mt-5" dangerouslySetInnerHTML={{ __html: sanitizedDescription }} />
                    <hr />
                    <h6 className="mt-5">Available pharmacies: </h6>
                    {pharmacyList.map(
                        pharmacy => (
                            <Link className="link-dark mr-2" key={pharmacy.pid} to={{
                                pathname: '/pharmacy_profile/' + pharmacy.pid
                            }}>{DOMPurify.sanitize(pharmacy.pharmacyName)}</Link>
                        )
                    )}
                </div>
            </div>
        </div>
    );
}

export default ViewMedicine;
