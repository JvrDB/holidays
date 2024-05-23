import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { createVacation, getVacation, updateVacation } from '../services/VacationService';
import { listVacationTypes } from '../services/VacationTypeService';

const VacationComponent = () => {
    const { employeeId, id } = useParams();
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [vacationTypes, setVacationTypes] = useState([]);
    const [vacationTypeId, setVacationTypeId] = useState('');
    const [errors, setErrors] = useState({
        startDate: '',
        endDate: '',
        vacationTypeId: ''
    });

    const navigate = useNavigate();

    useEffect(() => {
        if (id) {
            getVacation(id).then((response) => {
                setStartDate(response.data.startDate);
                setEndDate(response.data.endDate);
                setVacationTypeId(response.data.vacationType.id);
            }).catch(error => {
                console.error(error);
            });
        }

        listVacationTypes().then((response) => {
            setVacationTypes(response.data);
        }).catch(error => {
            console.error(error);
        });
    }, [id]);

    function saveOrUpdateVacation(e) {
        e.preventDefault();

        if (validateForm()) {
            const vacation = { startDate, endDate, vacationType:{id: vacationTypeId}, employee:{id:employeeId} };
            console.log(vacation);

            if (id) {
                updateVacation(id, vacation).then((response) => {
                    console.log(response.data);
                    navigate(`/view-employee/${employeeId}`);
                }).catch(error => {
                    console.error(error);
                });
            } else {
                createVacation(vacation).then((response) => {
                    console.log(response.data);
                    navigate(`/view-employee/${employeeId}`);
                }).catch(error => {
                    console.error(error);
                });
            }
        }
    }

    function validateForm() {
        let valid = true;
        const errorsCopy = { ...errors };

        if (startDate.trim()) {
            errorsCopy.startDate = '';
        } else {
            errorsCopy.startDate = 'Start Date is required';
            valid = false;
        }

        if (endDate.trim()) {
            errorsCopy.endDate = '';
        } else {
            errorsCopy.endDate = 'End Date is required';
            valid = false;
        }

        if (vacationTypeId) {
            errorsCopy.vacationTypeId = '';
        } else {
            errorsCopy.vacationTypeId = 'Vacation Type is required';
            valid = false;
        }

        setErrors(errorsCopy);
        return valid;
    }

    function pageTitle() {
        return id ? <h2 className='text-center'>Update Vacation</h2> : <h2 className='text-center'>Add Vacation</h2>;
    }

    return (
        <div className='container'>
            <br />
            <div className='row'>
                <div className='card col-md-6 offset-md-3 offset-md-3'>
                    {pageTitle()}
                    <div className='card-body'>
                        <form>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Start Date:</label>
                                <input 
                                    type='date'
                                    placeholder='Enter Start Date'
                                    name='startDate'
                                    value={startDate}
                                    className={`form-control ${errors.startDate ? 'is-invalid' : ''}`}
                                    onChange={(e) => setStartDate(e.target.value)}
                                />
                                {errors.startDate && <div className='invalid-feedback'>{errors.startDate}</div>}
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-label'>End Date:</label>
                                <input 
                                    type='date'
                                    placeholder='Enter End Date'
                                    name='endDate'
                                    value={endDate}
                                    className={`form-control ${errors.endDate ? 'is-invalid' : ''}`}
                                    onChange={(e) => setEndDate(e.target.value)}
                                />
                                {errors.endDate && <div className='invalid-feedback'>{errors.endDate}</div>}
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Vacation Type:</label>
                                <select
                                    className={`form-control ${errors.vacationTypeId ? 'is-invalid' : ''}`}
                                    value={vacationTypeId}
                                    onChange={(e) => setVacationTypeId(e.target.value)}
                                >
                                    <option value=''>Select Vacation Type</option>
                                    {vacationTypes.map((type) => (
                                        <option key={type.id} value={type.id}>
                                            {type.description}
                                        </option>
                                    ))}
                                </select>
                                {errors.vacationTypeId && <div className='invalid-feedback'>{errors.vacationTypeId}</div>}
                            </div>
                            <button className='btn btn-success' onClick={saveOrUpdateVacation}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default VacationComponent;