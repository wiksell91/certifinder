import {useState, useEffect} from 'react'

import {Empty, Layout, Menu, Spin, Table} from 'antd';
import {
    ContactsOutlined,
    LoadingOutlined,
    MailOutlined,
    UploadOutlined,
    UserOutlined,
    VideoCameraOutlined
} from '@ant-design/icons';
import {getAllCert, getAllOrders, gettAllCertusers} from "./client";
import './App.css';

const {Header, Content, Footer, Sider} = Layout;

// Columns/table
const users = [
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


function App() {
const antIcon = <LoadingOutlined style={{ fontSize: 24}} spin />;

    const [orderreqs, setOrderreqs] = useState([]);
    const [certificatestatus, setCertificatestatus] = useState([]);
    const [fetching, setFetching] = useState(true);

    const fetchCertificatestatus = () =>
        getAllCert()
            .then(res => res.json())
            .then(data => {
                setCertificatestatus(data);
                setFetching(false)
            })

    useEffect(() => {
        fetchCertificatestatus();
    }, []);

    const renderCertificatestatus = () => {
        if(fetching) {
            return <Spin indicator={antIcon}/>
        }
        if (certificatestatus.length <= 0) {
            return <Empty />;
        }
        return <Table dataSource={certificatestatus}
                      columns={users}
                      bordered
                      title={() => 'Våra Certifinders'}
                        pagination={{pageSize: 15}}
                      //scroll={{y:250}}
                      rowKey={(certificatestatus) => certificatestatus.certuser.id}
        />;
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
                return renderCertificatestatus();
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


//Mall
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

        //Mall slut
    }

    export default App;
