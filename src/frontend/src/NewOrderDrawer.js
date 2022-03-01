import {Drawer, Input, Col, Select, Form, Row, Button, Spin} from 'antd';
import TextArea from "antd/es/input/TextArea";
import {LoadingOutlined} from "@ant-design/icons";
import {useState} from 'react';
import {addNewOrder} from "./client";
import {successNotification, errorNotification} from "./Notification";

const {Option} = Select;

const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;

function NewOrderDrawer({showDrawer, setShowDrawer, fetchCertuser}) {
    const onCLose = () => setShowDrawer(false);
    const [submitting, setSubmitting] = useState(false);


    const onFinish = (certuserId, orderreq) => {
        setSubmitting(true)
        addNewOrder(certuserId, orderreq)
            .then(() => {
                console.log("student added")
                onCLose();
                successNotification(
                    "Student successfully added"
                )
                fetchCertuser();
            }).catch(err => {
            console.log(err)
        }).finally(() => {
            setSubmitting(false);
        })
    };





    const onFinishFailed = errorInfo => {
        alert(JSON.stringify(errorInfo, null, 2));
    };
    const { TextArea } = Input;

    return <Drawer
        title="Skapa ny förfrågan"
        width={720}
        onClose={onCLose}
        visible={showDrawer}
        bodyStyle={{paddingBottom: 80}}
        footer={
            <div
                style={{
                    textAlign: 'right',
                }}
            >
                <Button onClick={onCLose} style={{marginRight: 8}}>
                    Avbryt
                </Button>
            </div>
        }
    >
        <Form layout="vertical"
              onFinishFailed={onFinishFailed}
              onFinish={onFinish}
              hideRequiredMark>
            <Row gutter={16}>
                <Col span={12}>
                    <Form.Item
                        name="orderstatus"
                        label="status"
                    >
                        <Select>
                            <Option value="OBESVARAD">Obesvarad</Option>
                        </Select>
                    </Form.Item>
                </Col>
                <Row gutter={16}>
                <Col span={12}>
                    <Form.Item
                        name="ordertype"
                        label="Ordertyp"
                        rules={[{required: true, message: 'Vänligen välj ordertyp'}]}
                    >
                        <Select placeholder="Vänligen välj ordertyp">
                            <Option value="tungalyft">Tungalyft</Option>
                            <Option value="Lastbil">Lastbil</Option>
                            <Option value="Truck">Truck</Option>
                            <Option value="Hetarbete">Hetarbete</Option>
                        </Select>
                    </Form.Item>
                </Col>
            </Row>
                <Col span={12}>
                    <Form.Item
                        name="comment"
                        label="Arbetsbeskrivning"
                        rules={[{required: true, message: 'Vänligen beskriv arbetet'}]}
                    >
                        <TextArea placeholder="Vänligen beskriv arbetet"/>
                    </Form.Item>
                </Col>
            </Row>

            <Row>
                <Col span={12}>
                    <Form.Item >
                        <Button type="primary" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>
                </Col>
            </Row>
            <Row>
                {submitting && <Spin indicator={antIcon} />}
            </Row>
        </Form>
    </Drawer>
}

export default NewOrderDrawer;