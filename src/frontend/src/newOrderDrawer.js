import {Drawer, Input, Col, Select, Form, Row, Button} from 'antd';
import TextArea from "antd/es/input/TextArea";

const {Option} = Select;

function newOrderDrawer({showDrawer, setShowDrawer}) {
    const onCLose = () => setShowDrawer(false);

    const onFinish = values => {
        alert(JSON.stringify(values, null, 2));
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
                        name="name"
                        label="Name"
                        rules={[{required: true, message: 'Please enter student name'}]}
                    >
                        <Input placeholder="Please enter student name"/>
                    </Form.Item>
                </Col>
                <Row gutter={16}>
                <Col span={12}>
                    <Form.Item
                        name="ordertype"
                        label="Ordertyp"
                        rules={[{required: true, message: 'Vänligen välj ordertyp'}]}
                    >
                        <Select placeholder="Please select a gender">
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
        </Form>
    </Drawer>
}

export default newOrderDrawer;