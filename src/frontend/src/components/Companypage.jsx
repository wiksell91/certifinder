import {useEffect, useState} from "react";
import {addNewOrder, getAllCert, getAllOrders} from "../client";
import {Button, Empty, Layout, Menu, Radio, Spin, Table, Popconfirm} from "antd";
import NewOrderDrawer from "../NewOrderDrawer";
import {ContactsOutlined, LoadingOutlined, MailOutlined, UploadOutlined, UserOutlined} from "@ant-design/icons";
import {successNotification, errorNotification} from "../Notification";
import '../App.css';


const {Header, Content, Footer, Sider} = Layout;





const NewOrder = (rowKey) => {

    const [showDrawer, setShowDrawer] = useState(false);
   return <NewOrderDrawer
        showDrawer = {showDrawer}
        setShowDrawer = {setShowDrawer}
       // rowKe = {rowKey}
    />

};



const users  =   [
    {
        title: 'Namn',
        dataIndex: ['certuser', 'name'],
        key: 'name',
    },
    {
        title: 'Ålder',
        dataIndex: ['certuser', 'age'],
        key: 'age',
    },
    {
        title: 'Stad',
        dataIndex: ['certuser', 'city'],
        key: 'city',
    },
    {
        title: 'Mail',
        dataIndex: ['certuser', 'email'],
        key: 'email',
    },
    {
        title: 'Behörighet',
        dataIndex: ['certificate', 'certType'],
        key: 'cert_type',
    },
    {
        title: 'Giltig t.o.m',
        dataIndex: 'validto',
        key: 'validto',
    },

    {
        title: 'Boka',
        key: 'action',
        render: (text, certstatus) =>
            <Radio.Group>
                <Popconfirm
                    placement='topRight'
                    title={`Är du säker på att du vill boka ${certstatus.certuser.name}`}
                    onConfirm={() => NewOrder()}
                    okText='Japp'
                    cancelText='Nä fan..'>
                    <Radio.Button value="small">Boka Certuser</Radio.Button>
                </Popconfirm>
            </Radio.Group>


    }
];

const orders = [
    {
        title: 'Namn',
        dataIndex: ['certuser', 'name'],
        key: 'name',
    },
    {
        title: 'Behörighetstyp',
        dataIndex: 'ordertype',
        key: 'ordertype',
    },
    {
        title: 'Kommentar',
        dataIndex:  'comment',
        key: 'comment',
    },
    {
        title: 'Arbetsdatum',
        dataIndex:  'orderdate',
        key: 'orderdate',
    },
    {
        title: 'Företag',
        dataIndex: ['company', 'companyname'],
        key: 'companyname',
    },

];

function Companypage(){
    const [certstatus, setCertstatus] = useState([]);
    const [orderreqs, setOrderreqs] = useState([]);
    const [fetching, setFetching] = useState(true);

    const antIcon = <LoadingOutlined style={{fontSize: 24}} spin/>;



    const fetchCertstatus = () =>
        getAllCert()
            .then(res => res.json())
            .then(data => {
                setCertstatus(data);
            }).catch(err => {
                    console.log(err.response)
                    err.response.json().then(res => {
                        console.log(res);
                        errorNotification(
                            "There was an issue",
                            `${res.message} [${res.status}] [${res.error}]`
                        )
                    });
                }).finally(() => setFetching(false))

    useEffect(() => {
        fetchCertstatus();
    }, []);

    const renderCertstatus = () => {
        if(fetching) {
            return <Spin indicator={antIcon}/>
        }
        if (certstatus.length <= 0) {
            return <Empty />;
        }

        return <Table dataSource={certstatus}
                      columns={users}
                      bordered
                      title={() => 'Våra Certifinders'}
                      pagination={{pageSize: 15}}
            //scroll={{y:250}}
                      rowKey={(certstatus) => certstatus.certuser.id}
        />
    }

    const fetchOrderreqs = () =>
        getAllOrders()
            .then(res => res.json())
            .then(data => {
                setOrderreqs(data);
                setFetching(false)
            })

    useEffect(() => {
        fetchOrderreqs();
    }, []);

    const renderOrderreqs = () => {
        if(fetching) {
            return <Spin indicator={antIcon}/>
        }
        if (orderreqs.length <= 0) {
            return <Empty />;
        }
        return <Table dataSource={orderreqs}
                      columns={orders}
                      bordered
                      title={() => 'Arbetsförfrågningar'}
                      pagination={{pageSize: 15}}
            //scroll={{y:250}}
                      rowKey={(orderreqs) => orderreqs.id}
        />;
    }
    const [selectedMenuItem, setSelectedMenuItem] = useState("1")
    const handleItemClick = (key) => {
        switch (key) {
            case "1":
                return renderCertstatus();
            case "2":
                return renderOrderreqs();
            case "3":
                return "feeeeeeliyggi";
            case "4":
                return "feeeeeel";
            default:
                break;

        }
    };

    return <Layout>
        <Sider
            breakpoint="lg"
            collapsedWidth="0"
            onBreakpoint={broken => {
                console.log(broken);
            }}
            onCollapse={(collapsed, type) => {
                console.log(collapsed, type);
            }}
        >
            <div className="logo"/>
            <Menu selectedKeys={selectedMenuItem} theme="dark" mode="inline" onClick={(e) =>
                setSelectedMenuItem(e.key)}>
                <Menu.Item key="1" icon={<ContactsOutlined/>}>
                    Sök behörigheter
                </Menu.Item>
                <Menu.Item key="2" icon={<MailOutlined/>}>
                    Förfrågningar
                </Menu.Item>
                <Menu.Item key="3" icon={<UploadOutlined/>}>
                    nav 3
                </Menu.Item>
                <Menu.Item key="4" icon={<UserOutlined/>}>
                    nav 4
                </Menu.Item>
            </Menu>
        </Sider>
        <Layout>
            <Header className="site-layout-sub-header-background" style={{padding: 0}}></Header>
            <Content style={{margin: '24px 16px 0'}}>
                <div className="site-layout-background" style={{padding: 24, minHeight: 360}}>

                    {handleItemClick(selectedMenuItem)}
                </div>
            </Content>
            <Footer style={{textAlign: 'center'}}>Certifinder ©2022 Created by Victor Wiksell</Footer>
        </Layout>
    </Layout>




}

export default Companypage;