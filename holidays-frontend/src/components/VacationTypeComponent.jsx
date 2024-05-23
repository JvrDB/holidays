import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { createVacationType, getVacationType, updateVacationType } from '../services/VacationTypeService'

const VacationTypeComponent = () => {

    const [description, setDescription] = useState('')
    const [maxDays, setMaxDays] = useState(0)

    const {id} = useParams();

    const [errors, setErrors] = useState({
        description: '',
        maxDays: ''
    })

    const navigator = useNavigate();

    useEffect(() => {
        if(id){
            getVacationType(id).then((response) => {
                setDescription(response.data.description);
                setMaxDays(response.data.maxDays);
            }).catch(error => {
                console.error(error);
            })
        }
    }, [id])

    function saveOrUpdateVacationType(e){
        e.preventDefault();

        if(validateForm()){

            const vacationType = {description, maxDays: parseInt(maxDays)}
            console.log(vacationType)

            if(id){
                updateVacationType(id, vacationType).then((response) => {
                    console.log(response.data);
                    navigator('/vacation-types')
                }).catch(error => {
                    console.error(error);
                })
            } else {
                createVacationType(vacationType).then((response) => {
                    console.log(response.data);
                    navigator('/vacation-types')
                }).catch(error => {
                    console.error(error);
                })
            }
        }
    }

    function validateForm(){
        let valid = true;

        const errorsCopy = {... errors}

        if(description.trim()){
            errorsCopy.description = '';
        } else {
            errorsCopy.description = 'Description is required';
            valid = false;
        }

        if(maxDays > 0){
            errorsCopy.maxDays = '';
        } else {
            errorsCopy.maxDays = 'Max Days is required and should be greater than 0';
            valid = false;
        }

        setErrors(errorsCopy);

        return valid;
    }
    function pageTitle(){
        if(id){
            return <h2 className='text-center'>Update Vacation Type</h2>
        }else{
            return <h2 className='text-center'>Add Vacation Type</h2>
        }
    }

  return (
    <div className='container'>
        <br/>
            <div className='row'>
                <div className='card col-md-6 offset-md-3 offset-md-3'>
                    {
                        pageTitle()
                    }
                    <div className='card-body'>
                        <form>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Description:</label>
                                <input 
                                    type='text'
                                    placeholder='Enter Vacation Type Description'
                                    name='description'
                                    value={description}
                                    className={`form-control ${ errors.description ? 'is-invalid': ''}`}
                                    onChange={(e) => setDescription(e.target.value)}
                                >
                                </input>
                                { errors.description && <div className='invalid-feedback'> { errors.description} </div>}
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Max Days:</label>
                                <input 
                                    type='number'
                                    placeholder='Enter Vacation Type Max Days'
                                    name='maxdays'
                                    value={maxDays}
                                    className={`form-control ${ errors.maxDays ? 'is-invalid': ''}`}
                                    onChange={(e) => setMaxDays(parseInt(e.target.value))}
                                >
                                </input>
                                { errors.maxDays && <div className='invalid-feedback'> { errors.maxDays} </div>}
                            </div>
                            <button className='btn btn-success' onClick={saveOrUpdateVacationType}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
    </div>
  )
}

export default VacationTypeComponent